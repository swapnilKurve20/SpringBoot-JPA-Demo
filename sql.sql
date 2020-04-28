create table users (id  bigserial not null, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), primary key (id))

alter table users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email)

create table userprofiles (id  bigserial not null, address varchar(255), gender varchar(255), user_id int8 not null, primary key (id))

alter table userprofiles add constraint FKtqbfj7b11m8k2cpi7yv4t3rav foreign key (user_id) references users

alter table userprofiles add constraint UK_7gi6g93e9tvq5r62npvi47p3y unique (user_id)

create table Posts (id  bigserial not null, description varchar(255), title varchar(255), user_profile_id int8 not null, primary key (id))

alter table Posts add constraint FKa6dhyadp5v8im0qyp5fddc3vv foreign key (user_profile_id) references userprofiles

create table liked_by (post_id int8 not null, user_profile_id int8 not null, primary key (post_id, user_profile_id))

alter table liked_by add constraint FKmcd3wbdopuheyfgapvqmapgcx foreign key (user_profile_id) references userprofiles

alter table liked_by add constraint FKovmnrqjijw7oh2h4h04gr00d5 foreign key (post_id) references Posts

create table Tags (Id  bigserial not null, name varchar(255), primary key (Id))

create table post_tags (post_id int8 not null, tag_id int8 not null, primary key (post_id, tag_id))

alter table post_tags add constraint FK9ewptq221pf3yju5alypmsyah foreign key (tag_id) references Tags

alter table post_tags add constraint FKhwp07ipobrqn2yfvs2doci2rt foreign key (post_id) references Posts