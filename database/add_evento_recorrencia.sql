-- Adição de campos para suportar eventos recorrentes
-- Seguindo o padrão do projeto: nomes em maiúsculas, colunas em maiúsculas com underscore

-- Adicionar coluna para indicar se o evento é recorrente
ALTER TABLE EVENTO ADD COLUMN IF NOT EXISTS RECORRENTE BOOLEAN DEFAULT FALSE;

-- Adicionar coluna para o tipo de recorrência (DIARIO, SEMANAL, MENSAL, ANUAL)
ALTER TABLE EVENTO ADD COLUMN IF NOT EXISTS TIPO_RECORRENCIA VARCHAR(20);

-- Adicionar coluna para a data fim da recorrência
ALTER TABLE EVENTO ADD COLUMN IF NOT EXISTS DATA_FIM_RECORRENCIA DATE;

-- Adicionar constraint para validar o tipo de recorrência
ALTER TABLE EVENTO ADD CONSTRAINT CHK_TIPO_RECORRENCIA 
    CHECK (TIPO_RECORRENCIA IN ('DIARIO', 'SEMANAL', 'MENSAL', 'ANUAL') OR TIPO_RECORRENCIA IS NULL);

-- Adicionar comentários sobre as novas colunas
COMMENT ON COLUMN EVENTO.RECORRENTE IS 'Indica se o evento é recorrente (TRUE) ou único (FALSE)';
COMMENT ON COLUMN EVENTO.TIPO_RECORRENCIA IS 'Tipo de recorrência: DIARIO, SEMANAL, MENSAL ou ANUAL';
COMMENT ON COLUMN EVENTO.DATA_FIM_RECORRENCIA IS 'Data limite para a recorrência do evento';

-- Índice para eventos recorrentes
CREATE INDEX IF NOT EXISTS IDX_EVENTO_RECORRENTE ON EVENTO(RECORRENTE);
