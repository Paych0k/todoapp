// document.addEventListener("DOMContentLoaded", () => {
//     fetchTasks();
// });
//
// function getCsrfToken() {
//     return document.getElementById('csrf-token').value;
// }
//
//
// function fetchTasks() {
//     fetch('/tasks')
//         .then(response => response.json())
//         .then(data => {
//             const taskList = document.getElementById('task-list');
//             taskList.innerHTML = '';
//
//             data.forEach(task => {
//                 const taskItem = document.createElement('li');
//                 taskItem.className = 'list-group-item d-flex justify-content-between align-items-center';
//                 taskItem.innerHTML = `
//                     <span>${task.title}</span>
//                     <div>
//                         <button class="btn btn-danger btn-sm" onclick="deleteTask(${task.id})">Удалить</button>
//                     </div>
//                 `;
//                 taskList.appendChild(taskItem);
//             });
//         })
//         .catch(error => console.error('Ошибка при загрузке задач:', error));
// }
// function addTask() {
//     const taskTitle = document.getElementById('task-title').value;
//
//     if (!taskTitle) {
//         alert('Название задачи не может быть пустым');
//         return;
//     }
//
//     const csrfToken = getCsrfToken();
//
//     fetch('/tasks', {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json',
//             'X-CSRF-TOKEN': csrfToken // Добавляем CSRF-токен в заголовок
//         },
//         body: JSON.stringify({ title: taskTitle })
//     })
//         .then(response => {
//             if (response.ok) {
//                 fetchTasks();
//                 document.getElementById('task-title').value = '';
//             } else {
//                 console.error('Ошибка при добавлении задачи:', response);
//             }
//         })
//         .catch(error => console.error('Ошибка при добавлении задачи:', error));
// }
//
// function deleteTask(taskId) {
//     const csrfToken = getCsrfToken();
//
//     fetch(`/tasks/${taskId}`, {
//         method: 'DELETE',
//         headers: {
//             'X-CSRF-TOKEN': csrfToken // Добавляем CSRF-токен в заголовок
//         }
//     })
//         .then(response => {
//             if (response.ok) {
//                 fetchTasks();
//             } else {
//                 console.error('Ошибка при удалении задачи:', response);
//             }
//         })
//         .catch(error => console.error('Ошибка при удалении задачи:', error));
// }
//
