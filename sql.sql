create table liked_by (post_id int8 not null, user_profile_id int8 not null, primary key (post_id, user_profile_id))
create table post_tags (post_id int8 not null, tag_id int8 not null, primary key (post_id, tag_id))
create table Posts (id  bigserial not null, description varchar(255), title varchar(255), user_profile_id int8 not null, primary key (id))
create table Tags (Id  bigserial not null, name varchar(255), primary key (Id))
create table userprofiles (id  bigserial not null, address varchar(255), gender varchar(255), user_id int8 not null, primary key (id))
create table users (id  bigserial not null, email varchar(255), first_name varchar(255), last_name varchar(255), password varchar(255), primary key (id))
alter table userprofiles drop constraint UK_7gi6g93e9tvq5r62npvi47p3y
alter table userprofiles add constraint UK_7gi6g93e9tvq5r62npvi47p3y unique (user_id)
alter table users drop constraint UK_6dotkott2kjsp8vw4d0m25fb7
alter table users add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email)
alter table liked_by add constraint FKmcd3wbdopuheyfgapvqmapgcx foreign key (user_profile_id) references userprofiles
alter table liked_by add constraint FKovmnrqjijw7oh2h4h04gr00d5 foreign key (post_id) references Posts
alter table post_tags add constraint FK9ewptq221pf3yju5alypmsyah foreign key (tag_id) references Tags
alter table post_tags add constraint FKhwp07ipobrqn2yfvs2doci2rt foreign key (post_id) references Posts
alter table Posts add constraint FKa6dhyadp5v8im0qyp5fddc3vv foreign key (user_profile_id) references userprofiles
alter table userprofiles add constraint FKtqbfj7b11m8k2cpi7yv4t3rav foreign key (user_id) references users



CREATE SEQUENCE public.users_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE public.users
(
    id bigint NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    email character varying(255) COLLATE pg_catalog."default",
    first_name character varying(255) COLLATE pg_catalog."default",
    last_name character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email)
);

CREATE SEQUENCE public.userprofiles_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE public.userprofiles
(
    id bigint NOT NULL DEFAULT nextval('userprofiles_id_seq'::regclass),
    address character varying(255) COLLATE pg_catalog."default",
    gender character varying(255) COLLATE pg_catalog."default",
    user_id bigint NOT NULL,
    CONSTRAINT userprofiles_pkey PRIMARY KEY (id),
    CONSTRAINT uk_7gi6g93e9tvq5r62npvi47p3y UNIQUE (user_id),
    CONSTRAINT fktqbfj7b11m8k2cpi7yv4t3rav FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE SEQUENCE public.posts_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE public.posts
(
    id bigint NOT NULL DEFAULT nextval('posts_id_seq'::regclass),
    description character varying(255) COLLATE pg_catalog."default",
    title character varying(255) COLLATE pg_catalog."default",
    user_profile_id bigint NOT NULL,
    CONSTRAINT posts_pkey PRIMARY KEY (id),
    CONSTRAINT fka6dhyadp5v8im0qyp5fddc3vv FOREIGN KEY (user_profile_id)
        REFERENCES public.userprofiles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE public.liked_by
(
    post_id bigint NOT NULL,
    user_profile_id bigint NOT NULL,
    CONSTRAINT liked_by_pkey PRIMARY KEY (post_id, user_profile_id),
    CONSTRAINT fkmcd3wbdopuheyfgapvqmapgcx FOREIGN KEY (user_profile_id)
        REFERENCES public.userprofiles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkovmnrqjijw7oh2h4h04gr00d5 FOREIGN KEY (post_id)
        REFERENCES public.posts (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE SEQUENCE public.tags_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE public.tags
(
    id bigint NOT NULL DEFAULT nextval('tags_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT tags_pkey PRIMARY KEY (id)
);

CREATE TABLE public.post_tags
(
    post_id bigint NOT NULL,
    tag_id bigint NOT NULL,
    CONSTRAINT post_tags_pkey PRIMARY KEY (post_id, tag_id),
    CONSTRAINT fk9ewptq221pf3yju5alypmsyah FOREIGN KEY (tag_id)
        REFERENCES public.tags (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkhwp07ipobrqn2yfvs2doci2rt FOREIGN KEY (post_id)
        REFERENCES public.posts (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);