
搜索中心点 search_center:   search_center_id
搜索关键字 search_keywords: search_keywords_id
搜索参数 search_param: search_param_id, search_center_id, search_keywords_id
搜索记录 search_record: search_record_id, search_param_id
搜索结果 search_result: search_result_id, search_record_id, bd_uid, advertiser_name

-- 创建广告商信息表
CREATE TABLE "public"."advertiser" (
  "advertiser_id" int4 NOT NULL DEFAULT nextval('advertiser_advertiser_id_seq'::regclass),
  "uid" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "tel" varchar(255) COLLATE "pg_catalog"."default",
  "imgTel" varchar(255) COLLATE "pg_catalog"."default",
  "addr" varchar(255) COLLATE "pg_catalog"."default",
  "displayImageUrl" varchar(255) COLLATE "pg_catalog"."default",
  "admin_info" jsonb,
  "imageUrls" varchar[] COLLATE "pg_catalog"."default",
  "detailUrl" varchar(255) COLLATE "pg_catalog"."default",
  "ext" jsonb,
  "remark" text COLLATE "pg_catalog"."default",
  "score" int2,
  "created_by" varchar(255) COLLATE "pg_catalog"."default",
  "created_time" timestamp(6) NOT NULL,
  "updated_by" varchar COLLATE "pg_catalog"."default",
  "updated_time" timestamp(6) NOT NULL,
  CONSTRAINT "advertiser_pkey" PRIMARY KEY ("advertiser_id"),
  CONSTRAINT "uniq_uid" UNIQUE ("uid")
)
;

ALTER TABLE "public"."advertiser" 
  OWNER TO "postgres";

COMMENT ON COLUMN "public"."advertiser"."advertiser_id" IS '广告商id';

COMMENT ON COLUMN "public"."advertiser"."uid" IS '百度地图id';

COMMENT ON COLUMN "public"."advertiser"."name" IS '广告商名称';

COMMENT ON COLUMN "public"."advertiser"."tel" IS '默认电话';

COMMENT ON COLUMN "public"."advertiser"."imgTel" IS '图片中的电话';

COMMENT ON COLUMN "public"."advertiser"."addr" IS '地址';

COMMENT ON COLUMN "public"."advertiser"."displayImageUrl" IS '百度地图列表页展示图片';

COMMENT ON COLUMN "public"."advertiser"."admin_info" IS '城市信息';

COMMENT ON COLUMN "public"."advertiser"."imageUrls" IS '图片数组';

COMMENT ON COLUMN "public"."advertiser"."detailUrl" IS '详情页地址';

COMMENT ON COLUMN "public"."advertiser"."ext" IS '拓展信息';

COMMENT ON COLUMN "public"."advertiser"."remark" IS '备注';

COMMENT ON COLUMN "public"."advertiser"."score" IS '评分';

COMMENT ON TABLE "public"."advertiser" IS '广告商表';