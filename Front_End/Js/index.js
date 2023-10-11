function loadTasks() {
    // Fetch and display tasks
    $.get('/api/tasks', function (data) {
        const tasksList = $('#tasks');
        tasksList.empty(); // Clear existing tasks

        data.forEach(task => {
            tasksList.append(`
                <li class="list-group-item">
                    <h5>${task.taskName}</h5>
                    <p>${task.taskDescription}</p>
                    <p>${task.taskCode}</p>
                    <button class="btn btn-danger" onclick="deleteTask(${task.taskId})">Delete</button>
                    <button class="btn btn-success" data-toggle="modal" data-target="#updateModal" onclick="showUpdateTask(${task.taskId})">Update</button>
                </li>
            `);
        });
    });
}

function addTask() {
    const taskName = $('#task-name').val();
    const taskDescription = $('#task-description').val();
    const taskCode = $('#task-code').val();

    if (taskName.trim() !== '') {
        $.post('/api/tasks', {
            taskName: taskName,
            taskDescription: taskDescription,
            taskCode: taskCode,
        }, function () {
            loadTasks();
            clearInputFields();
        });
    }
}

function deleteTask(taskId) {
    $.ajax({
        url: `/api/tasks/${taskId}`,
        type: 'DELETE',
        success: function () {
            loadTasks();
        }
    });
}

function showUpdateTask(taskId) {
    // Fetch and display task details for update
    $.get(`/api/tasks/${taskId}`, function (task) {
        $('#updateModal #task-id').val(task.taskId);
        $('#updateModal #update-task-name').val(task.taskName);
        $('#updateModal #update-task-description').val(task.taskDescription);
        $('#updateModal #update-task-code').val(task.taskCode);
    });
}

function updateTask() {
    const taskId = $('#updateModal #task-id').val();
    const updatedTask = {
        taskName: $('#updateModal #update-task-name').val(),
        taskDescription: $('#updateModal #update-task-description').val(),
        taskCode: $('#updateModal #update-task-code').val(),
    };

    $.ajax({
        url: `/api/tasks/${taskId}`,
        type: 'PUT',
        data: JSON.stringify(updatedTask),
        contentType: 'application/json',
        success: function () {
            $('#updateModal').modal('hide');
            loadTasks();
        }
    });
}

function clearInputFields() {
    $('#task-name').val('');
    $('#task-description').val('');
    $('#task-code').val('');
}
