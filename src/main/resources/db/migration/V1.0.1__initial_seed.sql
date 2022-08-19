INSERT INTO usage_status ("usage_status_id", "usage_status_name", "usage_status_desc")
VALUES
(1,'used','normal usage'),
(2,'under-used','under usage'),
(3,'over-used','over usage');

INSERT INTO project_status ("project_status_id", "project_status_name", "project_status_desc")
VALUES
(1,'not-started','not yet started'),
(2,'active','ongoing'),
(3,'pending','on hold'),
(4,'done','finished');

INSERT INTO groups ("group_id", "group_name", "group_desc")
VALUES
(1,'not-system-admin','main org system admin'),
(2,'admin','normal admin per org'),
(3,'site-manager','project manager on site'),
(4,'fore-men','site fore men');

INSERT INTO user_types ("user_type_id", "user_type_name", "user_type_desc")
VALUES
(1,'normal','main org system admin'),
(2,'admin','site fore men');

INSERT INTO item_types ("item_type_id", "item_type_name", "item_type_desc")
VALUES
(1,'trackable','trackable items');

INSERT INTO organization ("org_id", "org_name", "org_physical_address")
VALUES
(1,'COSMOS','NAIROBI');


INSERT INTO roles ("role_id", "role_name", "role_desc")
VALUES
(1,'createUser','create users'),
(2,'updateUser','update users'),
(3,'deleteUser','delete user'),
(4,'getList','get users list'),
(5,'getUserById','get user'),
(6,'getAllUsers','project manager on site'),
(7,'createGroup','project manager on site'),
(8,'updateGroup','project manager on site'),
(9,'deleteGroup','project manager on site'),
(10,'createProjectFloor','project manager on site'),
(11,'updateProjectFloor','project manager on site'),
(12,'getProjectFloorById','project manager on site'),
(13,'deleteProjectFloor','project manager on site'),
(14,'updateItemUsed','project manager on site'),
(15,'createFloorItem','project manager on site'),
(16,'updateFloorItem','project manager on site'),
(17,'deleteFloorItem','project manager on site'),
(18,'deleteProject','project manager on site'),
(19,'updateProject','project manager on site'),
(20,'updateOrganization','project manager on site'),
(21,'deleteOrganization','site fore-men'),
(22,'createDamagedItem','record item damaged'),
(23,'updateDamagedItem','update item damaged'),
(24,'deleteDamagedItem','delete item damaged');


INSERT INTO role_groups ("role_group_id","role_group_group_id","role_group_role_id")
VALUES
(1,1,1),
(2,1,2),
(3,1,3),
(4,1,4),
(5,1,5),
(6,1,6),
(7,1,7),
(8,1,8),
(9,1,9),
(10,1,10),
(11,1,11),
(12,1,12),
(13,1,13),
(14,1,14),
(15,1,15),
(16,1,16),
(17,1,17),
(18,1,18),
(19,1,19),
(20,1,20),
(21,1,21),
(22,1,22),
(23,1,23),
(24,1,24);


