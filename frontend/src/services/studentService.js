const API_URL = 'http://localhost:8000/api/v1/students';

async function handleResponse(response) {
  if (!response.ok) {
    let message = 'Ocurrió un error al comunicarse con el backend';

    try {
      const errorBody = await response.json();
      message = errorBody.message || errorBody.error || message;
    } catch {
      // Si la respuesta no viene en JSON, se conserva el mensaje general.
    }

    throw new Error(message);
  }

  if (response.status === 204) {
    return null;
  }

  return response.json();
}

export async function getStudents() {
  const response = await fetch(API_URL);
  return handleResponse(response);
}

export async function createStudent(student) {
  const response = await fetch(API_URL, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(student)
  });

  return handleResponse(response);
}

export async function updateStudent(id, student) {
  const response = await fetch(`${API_URL}/${id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(student)
  });

  return handleResponse(response);
}

export async function deleteStudent(id) {
  const response = await fetch(`${API_URL}/${id}`, {
    method: 'DELETE'
  });

  return handleResponse(response);
}
