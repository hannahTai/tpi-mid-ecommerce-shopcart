CREATE
    TABLE
        cart (
            cart_id varchar(60) NOT NULL,
            created_date timestamp NOT NULL,
            customer_name varchar(60) NOT NULL,
            last_modified_date timestamp NULL,
            order_id varchar(60) NULL,
            CONSTRAINT cart_pkey PRIMARY KEY (cart_id)
        );

CREATE
    TABLE
        cart_item (
            cart_id varchar(255) NOT NULL,
            goods_id varchar(255) NOT NULL,
            count int4 NOT NULL,
            created_date timestamp NOT NULL,
            last_modified_date timestamp NULL,
            CONSTRAINT cart_item_pkey PRIMARY KEY (cart_id, goods_id)
        );
