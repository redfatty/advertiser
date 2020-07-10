
-- 搜索结果表
CREATE TABLE "public"."search_result" (
  "search_result_id" int4 NOT NULL DEFAULT nextval('search_result_search_result_id_seq'::regclass),
  "is_active" bool NOT NULL DEFAULT true,
  "search_action_id" int4 NOT NULL,
  "bd_uid" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "advertiser_name" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "distance" int2 NOT NULL DEFAULT 0,
  "created_by" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "created_time" timestamp(6) NOT NULL DEFAULT now(),
  CONSTRAINT "search_result_pkey" PRIMARY KEY ("search_result_id")
)
;

ALTER TABLE "public"."search_result" 
  OWNER TO "postgres";

COMMENT ON COLUMN "public"."search_result"."search_result_id" IS '搜索结果id';

COMMENT ON COLUMN "public"."search_result"."is_active" IS '是否有效';

COMMENT ON COLUMN "public"."search_result"."search_action_id" IS '搜索记录search_action.search_action_id';

COMMENT ON COLUMN "public"."search_result"."bd_uid" IS '广告商id, advertiser.bd_uid';

COMMENT ON COLUMN "public"."search_result"."advertiser_name" IS '广告商名称';

COMMENT ON COLUMN "public"."search_result"."distance" IS '与中心点的距离,单位米';

COMMENT ON TABLE "public"."search_result" IS '搜索结果';