
-- 搜索中心点表
CREATE TABLE "public"."search_center" (
  "search_center_id" int4 NOT NULL DEFAULT nextval('search_center_search_center_id_seq'::regclass),
  "is_active" bool NOT NULL DEFAULT true,
  "center_uid" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "center_name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "center_addr" varchar(200) COLLATE "pg_catalog"."default",
  "center_address_norm" varchar(300) COLLATE "pg_catalog"."default",
  "center_area_id" varchar COLLATE "pg_catalog"."default" NOT NULL,
  "center_area_name" varchar(100) COLLATE "pg_catalog"."default",
  "center_city_id" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "created_by" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "updated_by" varchar(50) COLLATE "pg_catalog"."default" NOT NULL,
  "created_time" timestamp(6) NOT NULL DEFAULT now(),
  "updated_time" timestamp(6) NOT NULL DEFAULT now(),
  CONSTRAINT "search_center_pkey" PRIMARY KEY ("search_center_id"),
  CONSTRAINT "uniq_center_uid" UNIQUE ("center_uid")
)
;

ALTER TABLE "public"."search_center" 
  OWNER TO "postgres";

COMMENT ON COLUMN "public"."search_center"."search_center_id" IS '搜索中心点id';

COMMENT ON COLUMN "public"."search_center"."is_active" IS '是否有效';

COMMENT ON COLUMN "public"."search_center"."center_uid" IS '中心点在百度地图中的唯一id';

COMMENT ON COLUMN "public"."search_center"."center_name" IS '中心点名称';

COMMENT ON COLUMN "public"."search_center"."center_addr" IS '中心点地址信息';

COMMENT ON COLUMN "public"."search_center"."center_area_id" IS '中心点所在区编码(百度指定的编码)';

COMMENT ON COLUMN "public"."search_center"."center_area_name" IS '中心点所在区名称';

COMMENT ON COLUMN "public"."search_center"."center_city_id" IS '中心点所在城市id(百度地图指定的编码)';

COMMENT ON TABLE "public"."search_center" IS '搜索中心点位置表';