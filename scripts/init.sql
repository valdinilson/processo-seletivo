BEGIN;

CREATE TABLE pessoa (
  pes_id SERIAL NOT NULL,
  pes_nome VARCHAR(200) NOT NULL,
  pes_data_nascimento DATE NOT NULL,
  pes_sexo VARCHAR(9) NOT NULL,
  pes_mae VARCHAR(200) NOT NULL,
  pes_pai VARCHAR(200),
  CONSTRAINT pk_pessoa PRIMARY KEY (pes_id)
);

CREATE TABLE endereco (
  end_id SERIAL NOT NULL,
  end_tipo_logradouro VARCHAR(50) NOT NULL,
  end_logradouro VARCHAR(200) NOT NULL,
  end_numero SMALLINT NOT NULL,
  end_bairro VARCHAR(100) NOT NULL,
  cid_id INTEGER NOT NULL,
  CONSTRAINT pk_endereco PRIMARY KEY (end_id)
);

CREATE TABLE cidade (
  cid_id SERIAL NOT NULL,
  cid_nome VARCHAR(200) NOT NULL,
  cid_uf VARCHAR(2) NOT NULL,
  CONSTRAINT pk_cidade PRIMARY KEY (cid_id)
);

CREATE TABLE foto_pessoa (
  fp_id SERIAL NOT NULL,
  fp_data DATE NOT NULL,
  fp_bucket VARCHAR(50) NOT NULL,
  fp_hash VARCHAR(50) NOT NULL,
  pes_id INTEGER NOT NULL,
  CONSTRAINT pk_foto_pessoa PRIMARY KEY (fp_id)
);

CREATE TABLE pessoa_endereco (
  end_id INTEGER NOT NULL,
  pes_id INTEGER NOT NULL,
  CONSTRAINT pk_pessoa_endereco PRIMARY KEY (end_id, pes_id)
);

CREATE TABLE servidor_temporario (
  pes_id INTEGER NOT NULL,
  st_data_admissao DATE NOT NULL,
  st_data_demissao DATE,
  CONSTRAINT pk_servidor_temporario PRIMARY KEY (pes_id)
);

CREATE TABLE servidor_efetivo (
  pes_id INTEGER NOT NULL,
  se_matricula VARCHAR(20) NOT NULL,
  CONSTRAINT pk_servidor_efetivo PRIMARY KEY (pes_id)
);

CREATE TABLE unidade (
  unid_id SERIAL NOT NULL,
  unid_nome VARCHAR(200) NOT NULL,
  unid_sigla VARCHAR(20) NOT NULL,
  CONSTRAINT pk_unidade PRIMARY KEY (unid_id)
);

CREATE TABLE unidade_endereco (
  unid_id INTEGER NOT NULL,
  end_id INTEGER NOT NULL,
  CONSTRAINT pk_unidade_endereco PRIMARY KEY (unid_id, end_id)
);

CREATE TABLE lotacao (
  lot_id SERIAL NOT NULL,
  lot_data_lotacao DATE NOT NULL,
  lot_data_remocao DATE,
  lot_portaria VARCHAR(100) NOT NULL,
  pes_id INTEGER NOT NULL,
  unid_id INTEGER NOT NULL,
  CONSTRAINT pk_lotacao PRIMARY KEY (lot_id)
);

CREATE TABLE usuario (
    usu_id SERIAL NOT NULL,
    usu_nome VARCHAR(20) NOT NULL,
    usu_email VARCHAR(80) NOT NULL,
    usu_senha VARCHAR(200) NOT NULL,
    CONSTRAINT pk_usuario PRIMARY KEY (usu_id)
);

ALTER TABLE IF EXISTS endereco ADD CONSTRAINT fk_endereco_cid_id FOREIGN KEY(cid_id) REFERENCES cidade;

ALTER TABLE IF EXISTS foto_pessoa ADD CONSTRAINT fk_foto_pessoa_pes_id FOREIGN KEY(pes_id) REFERENCES pessoa;

ALTER TABLE IF EXISTS pessoa_endereco ADD CONSTRAINT fk_pessoa_endereco_pes_id FOREIGN KEY(pes_id) REFERENCES pessoa;

ALTER TABLE IF EXISTS pessoa_endereco ADD CONSTRAINT fk_pessoa_endereco_end_id FOREIGN KEY(end_id) REFERENCES endereco;

ALTER TABLE IF EXISTS servidor_temporario ADD CONSTRAINT fk_servidor_temporario_pes_id FOREIGN KEY(pes_id) REFERENCES pessoa;

ALTER TABLE IF EXISTS servidor_efetivo ADD CONSTRAINT fk_servidor_efetivo_pes_id FOREIGN KEY(pes_id) REFERENCES pessoa;

ALTER TABLE IF EXISTS unidade_endereco ADD CONSTRAINT fk_unidade_endereco_unid_id FOREIGN KEY(unid_id) REFERENCES unidade;

ALTER TABLE IF EXISTS unidade_endereco ADD CONSTRAINT fk_unidade_endereco_end_id FOREIGN KEY(end_id) REFERENCES endereco;

ALTER TABLE IF EXISTS lotacao ADD CONSTRAINT fk_lotacao_pes_id FOREIGN KEY(pes_id) REFERENCES pessoa;

ALTER TABLE IF EXISTS lotacao ADD CONSTRAINT fk_lotacao_unid_id FOREIGN KEY(unid_id) REFERENCES unidade;

END;
