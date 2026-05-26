const API_BASE_URL = 'http://localhost:8000/api/v1/parking';

function buildHeaders(accessToken) {
  const headers = {
    'Content-Type': 'application/json'
  };

  if (accessToken) {
    headers.Authorization = `Bearer ${accessToken}`;
  }

  return headers;
}

async function handleResponse(response) {
  if (!response.ok) {
    let message = 'Ocurrió un error al comunicarse con el backend.';

    try {
      const errorBody = await response.json();
      message = errorBody.message || errorBody.error || message;
    } catch {
      // Se conserva el mensaje general si la respuesta no viene en JSON.
    }

    throw new Error(message);
  }

  if (response.status === 204) {
    return null;
  }

  return response.json();
}

export async function getParkingSpaces(accessToken) {
  const response = await fetch(`${API_BASE_URL}/spaces`, {
    headers: buildHeaders(accessToken)
  });

  return handleResponse(response);
}

export async function reserveParkingSpace(reservationData, accessToken) {
  const response = await fetch(`${API_BASE_URL}/reservations`, {
    method: 'POST',
    headers: buildHeaders(accessToken),
    body: JSON.stringify(reservationData)
  });

  return handleResponse(response);
}

export async function occupyReservation(reservationId, accessToken) {
  const response = await fetch(`${API_BASE_URL}/reservations/${reservationId}/occupy`, {
    method: 'PUT',
    headers: buildHeaders(accessToken)
  });

  return handleResponse(response);
}

export function createParkingEventSource() {
  return new EventSource(`${API_BASE_URL}/events`);
}