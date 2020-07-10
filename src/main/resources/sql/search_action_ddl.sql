
-- 搜索记录表
CREATE TABLE "public"."search_action" (
  "search_action_id" int4 NOT NULL DEFAULT nextval('search_action_search_action_id_seq'::regclass),
  "is_active" bool NOT NULL DEFAULT true,
  "search_center_id" int4,
  "search_keywords_id" int4 NOT NULL,
  "created_by" varchar(100) COLLATE "pg_catalog"."default",
  "created_time" timestamp(6) NOT NULL DEFAULT now(),
  CONSTRAINT "search_action_pkey" PRIMARY KEY ("search_action_id")
)
;

ALTER TABLE "public"."search_action" 
  OWNER TO "postgres";

COMMENT ON COLUMN "public"."search_action"."search_action_id" IS '搜索记录id';

COMMENT ON COLUMN "public"."search_action"."is_active" IS '是否有效';

COMMENT ON COLUMN "public"."search_action"."search_center_id" IS '搜索中心点search_center.search_center_id';

COMMENT ON COLUMN "public"."search_action"."search_keywords_id" IS '搜索关键字search_keywords.search_keywords_id';

COMMENT ON TABLE "public"."search_action" IS '搜索记录';