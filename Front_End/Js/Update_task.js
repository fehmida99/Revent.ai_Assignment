function showUpdateTask() {
    const taskId = new URLSearchParams(window.location.search).get('taskId');
    if (!taskId) {
        alert('Task ID is missing.');
        return;
    }

    // Fetch and display task details for update
    $.get(`/api/tasks/${taskId}`, function (task) {
        $('#task-id').val(task.taskId);
        $('#update-task-name').val(task.taskName);
        $('#update-task-description').val(task.taskDescription);
        $('#update-task-code').val(task.taskCode);
    });
}

function updateTask() {
    const taskId = $('#task-id').val();
    const updatedTask = {
        taskName: $('#update-task-name').val(),
        taskDescription: $('#update-task-description').val(),
        taskCode: $('#update-task-code').val(),
    };

    $.ajax({
        url: `/api/tasks/${taskId}`,
        type: 'PUT',
        data: JSON.stringify(updatedTask),
        contentType: 'application/json',
        success: function () {
            window.location.href = 'index.html'; // Redirect to the task list page after updating the task
        }
    });
}
