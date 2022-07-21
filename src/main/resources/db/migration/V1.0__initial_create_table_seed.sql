

-- Create org table
CREATE TABLE IF NOT EXISTS organization (
    "org_id" SERIAL PRIMARY KEY ,
    "org_name" VARCHAR(50) NOT NULL,
    "org_logo" BYTEA NULL,
    "org_physical_address" VARCHAR(50) NOT NULL,
    "org_postal_address" VARCHAR(15),
    "org_mobile_number" VARCHAR(15),
    "org_email" VARCHAR(30) NULL UNIQUE);

-- Create user_types table
CREATE TABLE IF NOT EXISTS user_types (
    "user_type_id" SERIAL PRIMARY KEY ,
    "user_type_name" VARCHAR(50) NOT NULL,
    "user_type_desc" VARCHAR(50) NULL);

    -- Create users table
CREATE TABLE IF NOT EXISTS users (
    "user_id" SERIAL PRIMARY KEY ,
    "user_password" VARCHAR(120) NOT NULL,
    "user_fullname" VARCHAR(50) NOT NULL,
    "user_mobile" VARCHAR(50) NULL UNIQUE,
    "user_email" VARCHAR(30) NULL UNIQUE,
    "user_type" SMALLINT NOT NULL REFERENCES user_types("user_type_id") ON DELETE SET NULL,
    "user_active" VARCHAR(3) DEFAULT 'N',
    "user_reset" VARCHAR(3) DEFAULT 'N');

    -- Create project_category table
CREATE TABLE IF NOT EXISTS project_category (
    "project_category_id" SERIAL PRIMARY KEY ,
    "project_category_name" VARCHAR(25) NOT NULL,
    "project_category_desc" VARCHAR(50) NULL);

    -- Create item_categories table
CREATE TABLE IF NOT EXISTS item_categories (
    "item_category_id" SERIAL PRIMARY KEY ,
    "item_category_name" VARCHAR(25) NOT NULL,
    "item_category_desc" VARCHAR(50) NULL);
    
       -- Create item_type table
CREATE TABLE IF NOT EXISTS item_types (
    "item_type_id" SERIAL PRIMARY KEY ,
    "item_type_name" VARCHAR(25) NOT NULL,
    "item_type_desc" VARCHAR(50) NULL,

    -- Create unit_type table
CREATE TABLE IF NOT EXISTS unit_type (
    "unit_id" SERIAL PRIMARY KEY ,
    "unit_name" VARCHAR(25) NOT NULL,
    "unit_desc" VARCHAR(50) NULL);

    -- Create items table
CREATE TABLE IF NOT EXISTS items (
    "item_id" SERIAL PRIMARY KEY ,
    "item_name" VARCHAR(120) NULL,
    "item_desc" VARCHAR(50) NOT NULL,
    "item_item_category_id" SMALLINT NOT NULL REFERENCES item_categories("item_category_id") ON DELETE SET NULL,
    "item_item_type_id" SMALLINT NOT NULL REFERENCES item_types("item_type_id") ON DELETE SET NULL,
    "item_unit_id" SMALLINT REFERENCES unit_type("unit_id") ON DELETE SET NULL);

    --create table project_status
CREATE TABLE IF NOT EXISTS project_status(
"project_status_id" SERIAL PRIMARY KEY,
"project_status_name" VARCHAR(10) NOT NULL,
"project_status_desc" VARCHAR(50) NULL);

    -- Create project table
CREATE TABLE IF NOT EXISTS project (
    "project_id" SERIAL PRIMARY KEY ,
    "project_org_id" SMALLINT REFERENCES organization("org_id") ON DELETE SET NULL,
    "project_name" VARCHAR(35) NUT NULL,
    "project_ref" VARCHAR(20) NULL,
    "project_desc" VARCHAR(50) NULL,
    "project_status" SMALLINT REFERENCES project_status("project_status_id") ON DELETE SET NULL,
    "project_creation_date" TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    "project_wef" TIMESTAMPTZ NULL,
    "project_wet" TIMESTAMPTZ NULL,
    "project_category_id" SMALLINT REFERENCES project_category("project_category_id") ON DELETE SET NULL,
    "project_items_selection_type" VARCHAR(25) NULL
    );


--create table usage_status
CREATE TABLE IF NOT EXISTS usage_status(
"usage_status_id" SERIAL PRIMARY KEY,
"usage_status_name" VARCHAR(10) NOT NULL,
"usage_status_desc" VARCHAR(50) NULL);


  -- Create project_floors table
CREATE TABLE IF NOT EXISTS project_floors (
    "project_floor_id" SERIAL PRIMARY KEY ,
    "project_floor_project_id" SMALLINT REFERENCES project("project_id") ON DELETE CASCADE,
    "project_floor_value" VARCHAR(35),
    "project_floor_ref" VARCHAR(20) NULL,
    "project_floor_measurement" NUMERIC(5,2) NULL,
    "project_floor_description" VARCHAR(50) NULL,
    "project_floor_status" SMALLINT REFERENCES project_status("project_status_id") ON DELETE SET NULL);


     --create project_floor_items table
    CREATE TABLE IF NOT EXISTS project_floor_item(
    "floor_item_id" SERIAL PRIMARY KEY,
    "floor_item_project_floor_id" SMALLINT REFERENCES floor_floor("floor_floor_id") ON DELETE SET NULL,
    "floor_item_item_id" SMALLINT REFERENCES items("item_id") ON DELETE SET NULL,
    "floor_item_normal_quantity" NUMERIC(3,2) NOT NULL,
    "floor_item_maximum_quantity" NUMERIC(3,2) NOT NULL,
    "floor_item_actual_quantity_used" NUMERIC(3,2) NOT NULL,
    "floor_item_status" SMALLINT REFERENCES usage_status(usage_status_id) ON DELETE SET NULL,
    "floor_item_status_report" VARCHAR(150) NULL
    );
    
      --create damaged_items table
    CREATE TABLE IF NOT EXISTS damaged_items(
    "damaged_id" SERIAL PRIMARY KEY,
    "damaged_date" DATE NOT NULL  ,
    "damaged_desc" varchar(200) NOT NULL,
    "damaged_floor_item_id" SMALLINT REFERENCES project_floor_item("floor_item_id") ON DELETE SET NULL,
    "damaged_quantity" SMALLINT NOT NULL
    );
    
    --create groups table
    CREATE TABLE IF NOT EXISTS groups (
  "group_id" SERIAL  PRIMARY KEY,
  "group_name" varchar(50) NOT NULL,
  "group_desc" varchar(50) NOT NULL);
  
  --create table user_groups
  CREATE TABLE IF NOT EXISTS group_users (
  "group_user_id" SERIAL PRIMARY KEY,
  "group_user_group_id" SMALLINT REFERENCES groups("group_id") ON DELETE CASCADE,
  "group_user_user_id" SMALLINT REFERENCES users("user_id") ON DELETE CASCADE;
);

--create table roles
CREATE TABLE IF NOT EXISTS roles (
  "role_id" SERIAL PRIMARY KEY,
  "role_name" varchar(160) NOT NULL,
  "role_desc" varchar(160) NOT NULL,
);

--create table role groups
CREATE TABLE IF NOT EXISTS role_groups(
"role_group_id" SERIAL PRIMARY KEY,
"role_group_group_id" SMALLINT REFERENCES groups("group_id"),
"role_group_role_id" SMALLINT REFERENCES roles("role_id")
);

--creat table project_users
CREATE TABLE IF NOT EXISTS PROJECT_USERS(
"project_user_id" SERIAL PRIMARY KEY,
"project_user_project_id" SMALLINT REFERENCES project("project_id") ON DELETE CASCADE,
"project_user_user_id" SMALLINT REFERENCES users("user_id") ON DELETE CASCADE);

  


