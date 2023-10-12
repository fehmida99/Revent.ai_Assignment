function addTask() {
    alert("Adding task")
    const taskName = $('#task-name').val();
    const taskDescription = $('#task-description').val();
    const taskCode = $('#task-code').val();

    if (taskName.trim() !== '') {
        $.post('http://localhost:8888/tasks/', {
            taskName: taskName,
            taskDescription: taskDescription,
            taskCode: taskCode,
        }, function () {
            window.location.href = 'all_tasks.html'; // Redirect to the "All Tasks" page after adding a new task
        });
    }
}
