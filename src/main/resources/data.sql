insert into delivery_partners(latitude, longitude, partner_name, partner_status) values
(37.7749, -122.4194, 'Partner A', 0),
(34.0522, -118.2437, 'Partner B', 1),
(40.7128, -74.0060, 'Partner C', 0),
(41.8781, -87.6298, 'Partner D', 0),
(29.7604, -95.3698, 'Partner E', 2);

insert into delivery_tasks(latitude, longitude, task_status, customer_id) values
(30.7749, -122.4194, 0, 1),
(38.0522, -128.2437, 1, 2),
(47.7128, -71.0060, 0, 3),
(49.8731, -81.6218, 0, 4),
(19.7604, -125.3698, 2, 5),
(27.7604, -94.3698, 3, 6);