use quanlysinhvien;

select * from student;

select * from student where `status` = true;

select *from `subject` where credit < 10;

select S.studentID, S.studentName, C.className
from  student S join class C on S.classID = C.classID;

select S.studentID, S.studentName, C.className
from student S join class C on S.classID = C.classID
where C.className = 'A1';

SELECT S.StudentId, S.StudentName, Sub.SubName, M.Mark
FROM Student S join Mark M on S.StudentId = M.StudentId join Subject Sub on M.SubId = Sub.SubId;

SELECT S.StudentId, S.StudentName, Sub.SubName, M.Mark
FROM Student S join Mark M on S.StudentId = M.StudentId join Subject Sub on M.SubId = Sub.SubId
WHERE Sub.SubName = 'CF';