DO
'
DECLARE
    user_tables CURSOR FOR
        select table_schema || ''.'' || table_name as table_name
        from information_schema.tables
        where table_schema IN (''public'')
          and table_type = ''BASE TABLE''
          and table_name not in (''databasechangelog'', ''databasechangeloglock'');
BEGIN
    FOR stmt IN user_tables LOOP
            EXECUTE ''TRUNCATE TABLE '' || stmt.table_name || '' CASCADE;'';
        END LOOP;
END;
' LANGUAGE plpgsql;

DO
'
DECLARE
    user_sequences CURSOR FOR
        select sequence_schema || ''.'' || sequence_name as sequence_name
        from information_schema.sequences
        where sequence_schema IN (''public'');
BEGIN
    FOR stmt IN user_sequences LOOP
            EXECUTE ''ALTER SEQUENCE '' || stmt.sequence_name::text || '' RESTART;'';
        END LOOP;
END;
' LANGUAGE plpgsql;

