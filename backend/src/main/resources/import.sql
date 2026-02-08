INSERT INTO servico (nome) 
SELECT 'Social Escort' WHERE NOT EXISTS (SELECT 1 FROM servico WHERE nome = 'Social Escort');

INSERT INTO servico (nome) 
SELECT 'Dinner Date' WHERE NOT EXISTS (SELECT 1 FROM servico WHERE nome = 'Dinner Date');

INSERT INTO servico (nome) 
SELECT 'Travel Companion' WHERE NOT EXISTS (SELECT 1 FROM servico WHERE nome = 'Travel Companion');

INSERT INTO servico (nome) 
SELECT 'Sensual Massage' WHERE NOT EXISTS (SELECT 1 FROM servico WHERE nome = 'Sensual Massage');

INSERT INTO servico (nome) 
SELECT 'Photo Shoot' WHERE NOT EXISTS (SELECT 1 FROM servico WHERE nome = 'Photo Shoot');