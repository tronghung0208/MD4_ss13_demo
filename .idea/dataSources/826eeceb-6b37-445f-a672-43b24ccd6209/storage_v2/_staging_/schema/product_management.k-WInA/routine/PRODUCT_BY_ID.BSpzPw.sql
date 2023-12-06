create
    definer = root@localhost procedure PRODUCT_BY_ID(IN idPram int)
BEGIN
    SELECT p.productId, p.productName, p.price, p.image, p.categoryId FROM products p WHERE p.productId = idPram;
END;
CALL PRODUCT_BY_ID()

