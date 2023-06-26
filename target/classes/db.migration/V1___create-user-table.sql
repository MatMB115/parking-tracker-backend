CREATE TABLE IF NOT EXISTS public.tb_user
(
    id_user serial NOT NULL,
    nome text COLLATE pg_catalog."default" NOT NULL,
    email text UNIQUE COLLATE pg_catalog."default" NOT NULL,
    senha character varying(100) COLLATE pg_catalog."default" NOT NULL,
    contato character varying(20) COLLATE pg_catalog."default" NOT NULL,
    tipo_usuario character(1) COLLATE pg_catalog."default" DEFAULT 'C'::bpchar,
    CONSTRAINT tb_user_pkey PRIMARY KEY (id_user),
    CONSTRAINT check_tipo_usuario CHECK (tipo_usuario = ANY (ARRAY['C'::bpchar, 'A'::bpchar]))
    )

CREATE TABLE IF NOT EXISTS public.tb_cliente
(
    id_cliente serial NOT NULL,
    id_rel_user integer NOT NULL,
    creditos integer NOT NULL,
    CONSTRAINT tb_cliente_pkey PRIMARY KEY (id_cliente),
    CONSTRAINT tb_cliente_id_user_fkey FOREIGN KEY (id_rel_user)
    REFERENCES public.tb_user (id_user) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS public.tb_agente
(
    id_agente serial NOT NULL,
    id_user integer NOT NULL,
    turno character(1) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT tb_agente_pkey PRIMARY KEY (id_agente),
    CONSTRAINT tb_agente_id_user_fkey FOREIGN KEY (id_user)
    REFERENCES public.tb_user (id_user) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE,
    CONSTRAINT check_turno CHECK (turno = ANY (ARRAY['M'::bpchar, 'T'::bpchar, 'N'::bpchar]))
    )