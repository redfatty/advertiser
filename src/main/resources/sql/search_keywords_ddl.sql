
-- 搜索关键字表
CREATE TABLE "public"."search_keywords" (
  "search_keywords_id" int4 NOT NULL DEFAULT nextval('search_keywords_search_keywords_id_seq'::regclass),
  "is_active" bool NOT NULL DEFAULT true,
  "keywords" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "created_by" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "updated_by" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "created_time" timestamp(6) NOT NULL DEFAULT now(),
  "updated_time" timestamp(6) NOT NULL DEFAULT now(),
  CONSTRAINT "search_keywords_pkey" PRIMARY KEY ("search_keywords_id"),
  CONSTRAINT "uniq_keywords" UNIQUE ("keywords")
)
;

ALTER TABLE "public"."search_keywords" 
  OWNER TO "postgres";

COMMENT ON COLUMN "public"."search_keywords"."search_keywords_id" IS '搜索关键字id';

COMMENT ON COLUMN "public"."search_keywords"."is_active" IS '是否有效';

COMMENT ON COLUMN "public"."search_keywords"."keywords" IS '关键字';

COMMENT ON TABLE "public"."search_keywords" IS '搜索关键字';