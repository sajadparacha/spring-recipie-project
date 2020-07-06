create table category (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB;
create table ingredients (id bigint not null auto_increment, amount decimal(19,2), description varchar(255), recipe_id bigint, uom_id bigint, primary key (id)) engine=InnoDB;
create table notes (id bigint not null auto_increment, recipe_notes varchar(255), recipe_id bigint, primary key (id)) engine=InnoDB;
create table recipe (id bigint not null auto_increment, cook_time integer, description varchar(255), difficulty varchar(255), directions longtext, image longblob, prep_time integer, serving integer, source varchar(255), url varchar(255), notes_id bigint, primary key (id)) engine=InnoDB;
create table recipe_category (recipe_id bigint not null, category_id bigint not null, primary key (recipe_id, category_id)) engine=InnoDB;
create table unit_of_measure (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB;
alter table ingredients add constraint FKiau49hpb0ahqg8r9mi42deywl foreign key (recipe_id) references recipe (id);
alter table ingredients add constraint FKfkljo7iwspj4of7h8bumypqxr foreign key (uom_id) references unit_of_measure (id);
alter table notes add constraint FKdbfsiv21ocsbt63sd6fg0t3c8 foreign key (recipe_id) references recipe (id);
alter table recipe add constraint FK37al6kcbdasgfnut9xokktie9 foreign key (notes_id) references notes (id);
alter table recipe_category add constraint FKqsi87i8d4qqdehlv2eiwvpwb foreign key (category_id) references category (id);
alter table recipe_category add constraint FKcqlqnvfyarhieewfeayk3v25v foreign key (recipe_id) references recipe (id);