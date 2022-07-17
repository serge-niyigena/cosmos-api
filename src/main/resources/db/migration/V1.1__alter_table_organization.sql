ALTER TABLE organization
ALTER COLUMN org_mobile_number TYPE VARCHAR(15);

ALTER TABLE project
RENAME COLUMN "project _name" TO "project_name";

ALTER TABLE project
ALTER COLUMN project_name SET NOT NULL;