CREATE SEQUENCE public.student_id_seq;
CREATE TABLE public.student
(
    id integer NOT NULL DEFAULT nextval('student_id_seq'::regclass),
    name character varying(70) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT student_pkey PRIMARY KEY (id)
);
CREATE SEQUENCE public.grade_id_seq;
CREATE TABLE public.grade
(
    id integer NOT NULL DEFAULT nextval('grade_id_seq'::regclass),
    id_student integer,
    id_course integer,
    grade_1 numeric(3,1),
    grade_2 numeric(3,1),
    grade_3 numeric(3,1),
    grade_4 numeric(3,1),
    CONSTRAINT grade_pkey PRIMARY KEY (id),
    CONSTRAINT grade_id_student_fkey FOREIGN KEY (id_student)
        REFERENCES public.student (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
    CONSTRAINT grade_id_course_fkey FOREIGN KEY (id_course)
        REFERENCES public.course (id) MATCH SIMPLE
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
        NOT VALID,
);
CREATE SEQUENCE public.course_id_seq;
CREATE TABLE public.course
(
    id integer NOT NULL DEFAULT nextval('course_id_seq'::regclass),
    name character varying(30) COLLATE pg_catalog."default",
    credit integer,
    CONSTRAINT course_pkey PRIMARY KEY (id)
);