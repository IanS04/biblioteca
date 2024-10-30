create database biblioteca;

use biblioteca;

create table TB_editora 
( id_editora int primary key not null ,
        nome varchar(60) not null 
);

create table TB_autor 
( id_autor int primary key not null,
      nome varchar(80) not null
);

create table TB_genero
( id_genero int primary key not null,
  nm_genero  varchar(80) not null
);

create table TB_funcionario
( id_funcionario int primary key not null,
		    nome varchar(80) not null,
			cpf  varchar(11) not null
);

create table TB_usuario
( id_usuario int primary key not null,
        nome varchar(80) not null,
         cpf varchar(11) not null,
dt_nascimento  date      not null 
);

create table TB_obra 
( id_obra int   not null ,
 titulo  varchar(100) not null,
 quantidade int  not null ,
 ano_publicacao date not null ,
 id_editora int not null,
 primary key (id_obra,id_editora),
 foreign key (id_editora) references TB_editora (id_editora)
 );

create table TB_endereco
(    cep  varchar(8) primary key not null ,
  cidade  varchar (80) not null,
  estado  varchar(80) not null , 
  numero  int not null,
	rua   varchar(80) not null,
complemento varchar (100),
fk_usuario int not null,
foreign key (fk_usuario) references TB_usuario (id_usuario)
);

create table TB_genero_obra
(id_genero int not null,
 id_obra  int not null,
 primary key (id_genero,id_obra),
 foreign key (id_genero) references TB_genero(id_genero), 
 foreign key (id_obra) references tb_obra (id_obra)
);

create table TB_obra_autor
( id_obra int not null,
  id_editora int not null,
  id_autor int not null ,
  primary key (id_obra,id_editora,id_autor),
  foreign key (id_obra)references tb_obra (id_obra) ,
  foreign key (id_editora) references tb_editora (id_editora),
  foreign key (id_autor) references tb_autor(id_autor)
);

create table TB_exemplar
(id_exemplar int not null,
 num_exemplar int not null,
 dt_aquisicao date not null,
 id_obra int not null,
 id_editora int not null ,
 primary key(id_exemplar),
 foreign key (id_obra) references tb_obra(id_obra),
 foreign key (id_editora) references tb_editora(id_editora)
);

create table TB_emprestimo
(id_emprestimo int not null,
dt_hr_emprestimo datetime not null,
id_usuario int not null,
primary key (id_emprestimo),
foreign key (id_usuario) references tb_usuario(id_usuario)
);

create table TB_devolucao
( id_devolucao int not null,
dt_hr_devolucao datetime not null ,
id_funcionario int not null,
id_multa int ,
id_emprestimo int not null,
primary key (id_devolucao),
foreign key (id_funcionario) references tb_funcionario(id_funcionario),
foreign  key (id_multa) references tb_multa(id_multa),
foreign key (id_emprestimo) references tb_emprestimo (id_emprestimo)
); 

create table TB_exemplar_emprestimo 
(id_exemplar int not null,
id_obra int not null,
id_editora int not null,
id_emprestimo int not null,
id_usuario int not null,
dt_de_devolucao date not null,
primary key (id_exemplar,id_obra,id_editora,id_emprestimo,id_usuario),
foreign key (id_exemplar) references Tb_exemplar (id_exemplar),
foreign key(id_emprestimo) references tb_emprestimo(id_emprestimo)
);

/todas os dados de obra, exemplares e editora das obras cadastradas/
SELECT titulo,quantidade,ano_publicacao,o.id_obra,a.id_editora,a.nome,id_exemplar,num_exemplar,dt_aquisicao
from tb_obra as o
join tb_editora as a 
on a.id_editora = o.id_editora
join  tb_exemplar  as e
on a.id_editora = e.id_editora ;


/Todas as obras e respectivo genero/
select titulo ,nm_genero 
from tb_obra as o 
join tb_genero_obra as g on o.id_obra = g.id_obra
join 
tb_genero as a on g.id_genero = a.id_genero ;

/*Todas as obras e autores do generos do romance */
select titulo,a.nome,nm_genero
from tb_autor as a join tb_obra_autor as oa on 
oa.id_autor = a.id_autor
join tb_obra as o on oa.id_obra = o.id_obra
join tb_genero_obra as ag on ag.id_obra = o.id_obra 
join tb_genero as g on ag.id_genero = g.id_genero 
where nm_genero = 'romance';

/Quantidade obras por genero/
select count(titulo),nm_genero
from tb_obra as o join tb_genero_obra as oa on 
o.id_obra = oa.id_obra 
join tb_genero as g on 
oa.id_genero = g.id_genero
group by nm_genero;

/Todos os dados de usuarios que fizeram emprestimos e qual obra foi emprestada./
select dt_hr_emprestimo ,nome ,titulo 
from tb_usuario as o join tb_emprestimo as e on
 o.id_usuario = e.id_usuario 
join tb_exemplar_emprestimo as em on
 em.id_emprestimo = e.id_emprestimo 
join tb_exemplar as ar on
 ar.id_exemplar = em.id_exemplar
join tb_obra as r on
 ar.id_obra = r.id_obra;

 /Todos os dados de emprestimos/
select titulo,dt_hr_emprestimo , valor,forma_pagamento
from tb_obra as o 
join tb_exemplar as e on 
o.id_obra = e.id_obra
join tb_exemplar_emprestimo as em on 
e.id_exemplar = em.id_exemplar
join tb_emprestimo as s on 
em.id_emprestimo = s.id_emprestimo 
join  tb_devolucao as d on 
d.id_emprestimo = s.id_emprestimo ;

/quantidade de exemplar por obra/
select num_exemplar,titulo
from tb_exemplar as e join tb_obra as o  on 
e.id_obra = o.id_obra ;

/Quantidade de obra por autores/
select count(o.id_obra) as quantidade_obra ,a.nome
from tb_obra as o join tb_obra_autor as oa on 
oa.id_obra = o.id_obra join 
tb_autor as a on a.id_autor = oa.id_autor
group by a.nome ;   

/Selecione todos as obras e seus respectivos generos que não possem emprestimos/
select titulo,nm_genero 
from tb_genero as g left join tb_genero_obra as go on 
g.id_genero = go.id_genero
left join tb_obra as o on
o.id_obra = go.id_obra
left join tb_exemplar as e on 
o.id_obra = e.id_obra
left join tb_exemplar_emprestimo as em on 
e.id_exemplar = em.id_exemplar
left join tb_emprestimo as s on 
em.id_emprestimo = s.id_emprestimo 
where em.id_exemplar is null;

/*view de usuario ,emprestimo,exemplar,exemplar_emprestimo,devolucao */
create view dados_emprestimo as 
select nome,cpf,dt_nascimento,o.id_emprestimo,o.dt_hr_emprestimo,e.id_exemplar,e.num_exemplar,dt_de_devolucao,d.id_devolucao,d.dt_hr_devolucao
from tb_exemplar as e  join tb_exemplar_emprestimo  as em on 
e.id_exemplar = em.id_exemplar 
join tb_emprestimo as o  on 
o.id_emprestimo = em.id_emprestimo join 
tb_devolucao as d on
o.id_emprestimo = d.id_emprestimo join tb_usuario as s on
s.id_usuario = o.id_usuario; 

/* obras cadastradas */
CREATE VIEW OBRAS_CADASTRADAS AS
SELECT titulo,quantidade,ano_publicacao,o.id_obra,a.id_editora,a.nome,id_exemplar,num_exemplar,dt_aquisicao
from tb_obra as o
join tb_editora as a 
on a.id_editora = o.id_editora
join  tb_exemplar  as e
on a.id_editora = e.id_editora ;


/obra adicionada procedure/
delimiter @ 
drop procedure if exists obra_adicionada @ 
create procedure obra_adicionada
( id_ob int ,nm_obra varchar(100) ,quant int , ano_pb date, id_ed int )
begin 
insert tb_obra (id_obra , titulo , quantidade , ano_publicacao , id_editora) 
values (id_ob , nm_obra, quant, ano_pb, id_ed );
end @ 
delimiter ;

call obra_adicionada ( 900,' mais perto do terraria ' ,2 ,'2020-08-13' ,12);

/exemplar adicionado/
delimiter @
drop procedure if exists exemplar_adicionado @
create procedure exemplar_adicionado 
(id_ex int ,nu_exemplar int ,dt_aqu date ,id_ob int ,id_edi int )
begin 
insert Tb_exemplar (id_exemplar,Num_exemplar,dt_aquisicao,id_obra,id_editora)
values (id_ex,nu_exemplar,dt_aqu,id_ob,id_edi);
end @
delimiter ;
call exemplar_adicionado (80,2,'2023-10-11' ,900,12);
call exemplar_adicionado (62,2,'2023-10-11' ,900,12);

/*obras devolvidas */
delimiter @
 drop trigger if exists obras_devolvidas @
 create  trigger obras_devolvidas after insert on  tb_emprestimo
 for each  row 
 begin 
 if new . dt_hr_emprestimo then 
 update  tb_exemplar set num_exemplar = num_exemplar + 1  
 where id_exemplar = id_exemplar ; 
 end if ;
 end  @ 
 delimiter ;