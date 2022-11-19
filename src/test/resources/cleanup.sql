DO
'
DECLARE
    user_tables CURSOR FOR
        select table_schema || ''.'' || table_name as table_name
        from information_schema.tables
        where table_schema IN (''market_tpl_carrier_planner_dev'', ''market_tpl_dev'', ''public'')
          and table_type = ''BASE TABLE''
          and table_name not in (''databasechangelog'',
                                 ''databasechangeloglock'',
                                 ''queue'',
                                 ''citizenship'',
                                 ''van_type'',
                                 ''report_template'');
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
        where sequence_schema IN (''market_tpl_carrier_planner_dev'', ''market_tpl_dev'', ''public'');
BEGIN
    FOR stmt IN user_sequences LOOP
            EXECUTE ''ALTER SEQUENCE '' || stmt.sequence_name::text || '' RESTART;'';
        END LOOP;
END;
' LANGUAGE plpgsql;

