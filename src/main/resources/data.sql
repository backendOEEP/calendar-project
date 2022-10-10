-- user 추가
insert into users(user_id, email, nickname, password) values
    ('a', 'a@mail.com', 'Changyun', '{noop}asdf1234')
;
insert into users(user_id, email, nickname, password) values
    ('b', 'b@mail.com', 'Minwoo', '{noop}asdf1234')
;
insert into users(user_id, email, nickname, password) values
    ('c', 'c@mail.com', 'Youngsun', '{noop}asdf1234')
;
insert into users(user_id, email, nickname, password) values
    ('d', 'd@mail.com', 'Jiyun', '{noop}asdf1234')
;

-- schedule 추가
insert into schedule(id, name, category, description, start_time, end_time, location, repeat_option) values
    (1, 'Backend Study', 'STUDY', 'backend study gogo', '2022-10-12 16:30:00', '2022-10-12 18:30:00', 'blindmelon', 'WEEK')
;

-- plan 추가
insert into plan(id, schedule_id, user_id) values
    (1, 1, 'a')
;
insert into plan(id, schedule_id, user_id) values
    (2, 1, 'b')
;
insert into plan(id, schedule_id, user_id) values
    (3, 1, 'c')
;
insert into plan(id, schedule_id, user_id) values
    (4, 1, 'd')
;
