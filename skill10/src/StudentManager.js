import React, { useState } from "react";
import "./StudentManager.css";

function StudentManager() {
  const [students, setStudents] = useState([
    { id: 1, name: "Ravi", course: "CSE" },
    { id: 2, name: "Anjali", course: "ECE" },
    { id: 3, name: "Kiran", course: "EEE" },
    { id: 4, name: "Sneha", course: "IT" },
    { id: 5, name: "Rahul", course: "MECH" }
  ]);

  const [newStudent, setNewStudent] = useState({
    id: "",
    name: "",
    course: ""
  });

  const handleChange = (e) => {
    setNewStudent({
      ...newStudent,
      [e.target.name]: e.target.value
    });
  };

  const addStudent = () => {
    if (!newStudent.id || !newStudent.name || !newStudent.course) {
      alert("Please fill all fields");
      return;
    }

    setStudents([...students, newStudent]);

    setNewStudent({
      id: "",
      name: "",
      course: ""
    });
  };

  const deleteStudent = (id) => {
    const updatedList = students.filter((student) => student.id !== id);
    setStudents(updatedList);
  };

  return (
    <div className="container">
      <h2>Student Manager</h2>

      <div className="form">
        <input type="number" name="id" placeholder="Enter ID" value={newStudent.id} onChange={handleChange}/>
        <input type="text" name="name" placeholder="Enter Name" value={newStudent.name} onChange={handleChange}/>
        <input type="text" name="course" placeholder="Enter Course" value={newStudent.course} onChange={handleChange}/>
        <button onClick={addStudent}>Add Student</button>
      </div>

      {students.length === 0 ? (
        <p className="empty">No students available</p>
      ) : (
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Course</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {students.map((student) => (
              <tr key={student.id}>
                <td>{student.id}</td>
                <td>{student.name}</td>
                <td>{student.course}</td>
                <td>
                  <button className="delete-btn" onClick={() => deleteStudent(student.id)}>
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default StudentManager;
