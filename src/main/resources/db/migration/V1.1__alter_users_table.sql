
ALTER TABLE users add user_org_id SMALLINT  REFERENCES organization("org_id") ON DELETE SET NULL;