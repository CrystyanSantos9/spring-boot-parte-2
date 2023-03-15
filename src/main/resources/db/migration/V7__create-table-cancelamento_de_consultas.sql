create table cancelamento_de_consultas(
   id bigint not null auto_increment,
   consulta_id bigint not null, 
   motivo varchar(100) not null,
   data_cancelamento datetime not null, 

   primary key(id),
   constraint fk_consulta_id foreign key(consulta_id) references consultas(id)
);