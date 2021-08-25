package dao

import model.Student

class StudentDao: Dao<Student, String> {
    private var students = arrayListOf<Student>().apply {
        add(Student("Tony bin Stark", "12345", "Teknik Informatika", "4IA01", "Universitas Gunadarma"))
        add(Student("Black Widow", "12346", "Teknik Informatika", "4IA01", "Universitas Gunadarma"))
        add(Student("Hawkeye bin Robin", "12347", "Teknik Informatika", "4IA01", "Universitas Gunadarma"))
        add(Student("Bruce bin Banner", "12348", "Teknik Informatika", "4IA01", "Universitas Gunadarma"))
        add(Student("Vision bin Tony Stark", "12349", "Teknik Informatika", "4IA01", "Universitas Gunadarma"))
    }

    override fun getData(): List<Student> {
        return students
    }

    override fun addData(item: Student) {
        students.add(item)
    }

    override fun deleteData(uniqueID: String) {
//        students.forEach {
//            if (it.nim == uniqueID) {
//                students.remove(it)
//            }
//        }

        students.remove(students.find { student -> student.nim == uniqueID })
    }

}