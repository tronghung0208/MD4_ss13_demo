create
    definer = root@localhost procedure PROC_GET_ALL_CATEGORY
BEGIN
    SELECT s.categoryId, s.categoryName,s.status  from categorys s ;
END;
