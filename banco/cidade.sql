/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : PostgreSQL
 Source Server Version : 110005
 Source Host           : localhost:5432
 Source Catalog        : senior
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 110005
 File Encoding         : 65001

 Date: 24/11/2019 22:52:37
*/


-- ----------------------------
-- Table structure for cidade
-- ----------------------------
DROP TABLE IF EXISTS "public"."cidade";
CREATE TABLE "public"."cidade" (
  "idcidade" int2 NOT NULL DEFAULT nextval('cidade_idcidade_seq'::regclass),
  "ibge_id" int4,
  "uf" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "capital" bool DEFAULT false,
  "lon" float8,
  "lat" float8,
  "no_accents" varchar(255) COLLATE "pg_catalog"."default",
  "alternative_names" varchar(255) COLLATE "pg_catalog"."default",
  "microregion" varchar(255) COLLATE "pg_catalog"."default",
  "mesoregion" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Primary Key structure for table cidade
-- ----------------------------
ALTER TABLE "public"."cidade" ADD CONSTRAINT "cidade_pkey" PRIMARY KEY ("idcidade");
