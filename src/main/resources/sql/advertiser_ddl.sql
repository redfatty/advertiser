
-- 广告商表
CREATE TABLE "public"."advertiser" (
  "advertiser_id" int4 NOT NULL DEFAULT nextval('advertiser_advertiser_id_seq'::regclass),
  "bd_uid" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "bd_auth" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "tel" varchar(100) COLLATE "pg_catalog"."default",
  "img_tel" varchar(100) COLLATE "pg_catalog"."default",
  "addr" varchar(300) COLLATE "pg_catalog"."default",
  "address_norm" varchar(500) COLLATE "pg_catalog"."default",
  "default_img_url" varchar(255) COLLATE "pg_catalog"."default",
  "album_img_urls" varchar[] COLLATE "pg_catalog"."default",
  "province_name" varchar(20) COLLATE "pg_catalog"."default",
  "city_name" varchar(20) COLLATE "pg_catalog"."default",
  "area_name" varchar(20) COLLATE "pg_catalog"."default",
  "aoi" varchar(100) COLLATE "pg_catalog"."default",
  "di_tag" varchar(100) COLLATE "pg_catalog"."default",
  "is_active" bool NOT NULL DEFAULT true,
  "created_by" varchar(20) COLLATE "pg_catalog"."default",
  "created_time" timestamp(6) NOT NULL DEFAULT now(),
  "updated_by" varchar(20) COLLATE "pg_catalog"."default",
  "updated_time" timestamp(6) NOT NULL DEFAULT now(),
  CONSTRAINT "advertiser_pkey" PRIMARY KEY ("advertiser_id"),
  CONSTRAINT "unq_bd_uid" UNIQUE ("bd_uid")
)
;

ALTER TABLE "public"."advertiser" 
  OWNER TO "postgres";

CREATE INDEX "idx_advertiser_id" ON "public"."advertiser" USING btree (
  "advertiser_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

CREATE INDEX "idx_bd_uid" ON "public"."advertiser" USING btree (
  "bd_uid" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

COMMENT ON COLUMN "public"."advertiser"."advertiser_id" IS '广告商表主键Id';

COMMENT ON COLUMN "public"."advertiser"."bd_uid" IS '百度地图唯一标识';

COMMENT ON COLUMN "public"."advertiser"."bd_auth" IS '百度auth';

COMMENT ON COLUMN "public"."advertiser"."name" IS '广告商名称';

COMMENT ON COLUMN "public"."advertiser"."tel" IS '默认电话';

COMMENT ON COLUMN "public"."advertiser"."img_tel" IS '从图片中识别出的电话';

COMMENT ON COLUMN "public"."advertiser"."addr" IS '地址';

COMMENT ON COLUMN "public"."advertiser"."address_norm" IS '地址详细信息';

COMMENT ON COLUMN "public"."advertiser"."default_img_url" IS '默认图片地址';

COMMENT ON COLUMN "public"."advertiser"."album_img_urls" IS '相册图片地址集合';

COMMENT ON COLUMN "public"."advertiser"."province_name" IS '省';

COMMENT ON COLUMN "public"."advertiser"."city_name" IS '市';

COMMENT ON COLUMN "public"."advertiser"."area_name" IS '区/县';

COMMENT ON COLUMN "public"."advertiser"."aoi" IS '所在地段别称';

COMMENT ON COLUMN "public"."advertiser"."di_tag" IS '标签';

COMMENT ON COLUMN "public"."advertiser"."is_active" IS '是否有效';

COMMENT ON COLUMN "public"."advertiser"."created_by" IS '创建人';

COMMENT ON COLUMN "public"."advertiser"."created_time" IS '创建时间';

COMMENT ON COLUMN "public"."advertiser"."updated_by" IS '更新人';

COMMENT ON COLUMN "public"."advertiser"."updated_time" IS '更新时间';

COMMENT ON TABLE "public"."advertiser" IS '广告商表';