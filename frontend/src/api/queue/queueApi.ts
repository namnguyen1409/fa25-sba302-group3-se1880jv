import { apiClient } from "../client";
import type { QueueTicketResponse } from "../models";

export const QueueApi = {
    getQueuesForDoctorToday: () =>
        apiClient.get<QueueTicketResponse[]>("/queue-tickets/doctor/today"),
    callQueueTicket: (queueTicketId: string) =>
        apiClient.post(`/queue-tickets/${queueTicketId}/call`),
    startQueueTicket: (queueTicketId: string) =>
        apiClient.post(`/queue-tickets/${queueTicketId}/start`),
    skipQueueTicket: (queueTicketId: string) =>
        apiClient.post(`/queue-tickets/${queueTicketId}/skip`),
    requeueQueueTicket: (queueTicketId: string) =>
        apiClient.post(`/queue-tickets/${queueTicketId}/requeue`),
    waitResultQueueTicket: (queueTicketId: string) =>
        apiClient.post(`/queue-tickets/${queueTicketId}/wait-result`),
    resumeQueueTicket: (queueTicketId: string) =>
        apiClient.post(`/queue-tickets/${queueTicketId}/resume`),
    doneQueueTicket: (queueTicketId: string) =>
        apiClient.post(`/queue-tickets/${queueTicketId}/done`),
}