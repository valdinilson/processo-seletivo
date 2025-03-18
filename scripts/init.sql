CREATE TABLE pessoa (
    pes_id INT GENERATED ALWAYS AS IDENTITY,
    pes_nome VARCHAR(200) NOT NULL,
    pes_data_nascimento DATE NOT NULL,
    pes_sexo VARCHAR(9) NOT NULL,
    pes_mae VARCHAR(200) NOT NULL,
    pes_pai VARCHAR(200) NOT NULL,
    CONSTRAINT pk_pessoa PRIMARY KEY (pes_id)
);

CREATE TABLE endereco (
    end_id INT GENERATED ALWAYS AS IDENTITY,
    end_tipo_logradouro VARCHAR(50) NOT NULL,
    end_logradouro VARCHAR(200) NOT NULL,
    end_numero INT NOT NULL,
    end_bairro VARCHAR(100) NOT NULL,
    cid_id INT NOT NULL,
    CONSTRAINT pk_endereco PRIMARY KEY (end_id)
);

CREATE TABLE cidade (
  cid_id INT GENERATED ALWAYS AS IDENTITY,
  cid_nome VARCHAR(200) NOT NULL,
  cid_uf char(2) NOT NULL,
  CONSTRAINT pk_cidade PRIMARY KEY (cid_id)
);

CREATE TABLE foto_pessoa (
  fp_id INT GENERATED ALWAYS AS IDENTITY,
  pes_id INT NOT NULL,
  fp_data DATE NOT NULL,
  fp_bucket VARCHAR(50) NOT NULL,
  fp_hash VARCHAR(50) NOT NULL,
  CONSTRAINT pk_foto_pessoa PRIMARY KEY (fp_id)
);

CREATE TABLE pessoa_endereco (
  pes_id INT NOT NULL,
  end_id INT NOT NULL,
  CONSTRAINT pk_pessoa_endereco PRIMARY KEY (pes_id, end_id)
);

CREATE TABLE servidor_temporario (
  pes_id INT NOT NULL,
  st_data_admissao DATE NOT NULL,
  st_data_demissao DATE
);

CREATE TABLE servidor_efetivo (
  pes_id INT NOT NULL,
  se_matricula VARCHAR(20) NOT NULL
);

CREATE TABLE unidade (
  unid_id INT GENERATED ALWAYS AS IDENTITY,
  unid_nome VARCHAR(200) NOT NULL,
  unid_sigla VARCHAR(20) NOT NULL,
  CONSTRAINT pk_unidade PRIMARY KEY (unid_id)
);

CREATE TABLE unidade_endereco (
  unid_id INT NOT NULL,
  end_id INT NOT NULL,
  CONSTRAINT pk_unidade_endereco PRIMARY KEY (unid_id, end_id)
);

CREATE TABLE lotacao (
  lot_id INT GENERATED ALWAYS AS IDENTITY,
  pes_id INT NOT NULL,
  unid_id INT NOT NULL,
  lot_data_lotacao DATE NOT NULL,
  lot_data_remocao DATE NOT NULL,
  lot_portaria VARCHAR(100) NOT NULL,
  CONSTRAINT pk_lotacao PRIMARY KEY (lot_id)
);

ALTER TABLE endereco ADD CONSTRAINT fk_endereco_cid_id FOREIGN KEY(cid_id) REFERENCES cidade (cid_id);

ALTER TABLE foto_pessoa ADD CONSTRAINT fk_foto_pessoa_pes_id FOREIGN KEY(pes_id) REFERENCES pessoa (pes_id);

ALTER TABLE pessoa_endereco ADD CONSTRAINT fk_pessoa_endereco_pes_id FOREIGN KEY(pes_id) REFERENCES pessoa (pes_id);

ALTER TABLE pessoa_endereco ADD CONSTRAINT fk_pessoa_endereco_end_id FOREIGN KEY(end_id) REFERENCES endereco (end_id);

ALTER TABLE servidor_temporario ADD CONSTRAINT fk_servidor_temporario_pes_id FOREIGN KEY(pes_id) REFERENCES pessoa (pes_id);

ALTER TABLE servidor_efetivo ADD CONSTRAINT fk_servidor_efetivo_pes_id FOREIGN KEY(pes_id) REFERENCES pessoa (pes_id);

ALTER TABLE unidade_endereco ADD CONSTRAINT fk_unidade_endereco_unid_id FOREIGN KEY(unid_id) REFERENCES unidade (unid_id);

ALTER TABLE unidade_endereco ADD CONSTRAINT fk_unidade_endereco_end_id FOREIGN KEY(end_id) REFERENCES endereco (end_id);

ALTER TABLE lotacao ADD CONSTRAINT fk_lotacao_pes_id FOREIGN KEY(pes_id) REFERENCES pessoa (pes_id);

ALTER TABLE lotacao ADD CONSTRAINT fk_lotacao_unid_id FOREIGN KEY(unid_id) REFERENCES unidade (unid_id);