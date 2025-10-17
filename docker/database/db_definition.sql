CREATE TABLE IF NOT EXISTS client (
                               id uuid DEFAULT gen_random_uuid() NOT NULL,
                               "name" varchar NOT NULL,
                               phone varchar NOT NULL,
                               email varchar NOT NULL,
                               CONSTRAINT client_unique UNIQUE (id)
);

CREATE TABLE IF NOT EXISTS company (
                                company_identifier varchar NOT NULL,
                                client_id uuid NOT NULL,
                                CONSTRAINT company_pk PRIMARY KEY (client_id),
                                CONSTRAINT company_unique UNIQUE (company_identifier),
                                CONSTRAINT company_client_fk FOREIGN KEY (client_id) REFERENCES public.client(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS person (
                               birth_date date NOT NULL,
                               client_id uuid NOT NULL,
                               CONSTRAINT person_pk PRIMARY KEY (client_id),
                               CONSTRAINT person_client_fk FOREIGN KEY (client_id) REFERENCES public.client(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS contract (
                                        id uuid DEFAULT gen_random_uuid() NOT NULL,
                                        start_date timestamp DEFAULT now() NOT NULL,
                                        update_date timestamp DEFAULT now() NOT NULL,
                                        end_date timestamp NULL,
                                        cost_amount numeric(15, 2) DEFAULT 0 NOT NULL,
                                        client_id uuid NULL,
                                        CONSTRAINT contract_pk PRIMARY KEY (id),
                                        CONSTRAINT contract_client_fk FOREIGN KEY (client_id) REFERENCES public.client(id)
);