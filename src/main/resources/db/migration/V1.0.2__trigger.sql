
CREATE OR REPLACE FUNCTION p_status()   
RETURNS trigger AS $p_status$ 
	 DECLARE num_floors BIGINT;
	 status BIGINT;
     BEGIN
        SELECT COUNT(*) INTO num_floors FROM project_floors WHERE project_floor_project_id = NEW.project_floor_project_id;
        SELECT COUNT(*) INTO status FROM project_floors WHERE project_floor_project_id = NEW.project_floor_project_id AND project_floor_status = NEW.project_floor_status;
        IF num_floors = status THEN
            UPDATE project SET project_status= NEW.project_floor_status WHERE project_id= NEW.project_floor_project_id;
        END IF;
    	END 
$p_status$
LANGUAGE plpgsql ;

  


CREATE TRIGGER p_status

  AFTER UPDATE ON project_floors
  FOR EACH ROW
  WHEN(OLD.project_floor_status IS DISTINCT FROM NEW.project_floor_status)
	EXECUTE FUNCTION p_status();
