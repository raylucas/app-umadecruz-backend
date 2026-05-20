-- Migração para adicionar campo de foto na tabela USUARIO
-- Autor: Sistema
-- Data: 2026-05-14
-- Descrição: Adiciona coluna FOTO para armazenar imagem de perfil do usuário em formato binário (BYTEA)

-- Adiciona coluna FOTO do tipo BYTEA (Binary Large Object)
-- BYTEA é o tipo nativo do PostgreSQL para armazenar dados binários
-- Mais eficiente que TEXT com base64 (sem overhead de 33%)
-- Adequado para imagens de perfil (geralmente < 5MB)
ALTER TABLE USUARIO 
ADD COLUMN IF NOT EXISTS FOTO BYTEA;

-- Comentário para documentação
COMMENT ON COLUMN USUARIO.FOTO IS 'Foto de perfil do usuário em formato binário (imagem). Armazenada como BYTEA para eficiência.';

-- Opcional: Criar índice se houver buscas frequentes por usuários com foto
-- CREATE INDEX IDX_USUARIO_FOTO ON USUARIO(FOTO) WHERE FOTO IS NOT NULL;
