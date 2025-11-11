# QueueTicketResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **string** |  | [optional] [default to undefined]
**assignedDoctor** | [**StaffResponse**](StaffResponse.md) |  | [optional] [default to undefined]
**assignedRoom** | [**RoomResponse**](RoomResponse.md) |  | [optional] [default to undefined]
**queueNumber** | **string** |  | [optional] [default to undefined]
**status** | **string** |  | [optional] [default to undefined]
**priority** | **string** |  | [optional] [default to undefined]
**examinationId** | **string** |  | [optional] [default to undefined]
**appointmentId** | **string** |  | [optional] [default to undefined]
**appointmentPatient** | [**PatientResponse**](PatientResponse.md) |  | [optional] [default to undefined]
**appointmentSpecialty** | [**SpecialtyResponse**](SpecialtyResponse.md) |  | [optional] [default to undefined]

## Example

```typescript
import { QueueTicketResponse } from './api';

const instance: QueueTicketResponse = {
    id,
    assignedDoctor,
    assignedRoom,
    queueNumber,
    status,
    priority,
    examinationId,
    appointmentId,
    appointmentPatient,
    appointmentSpecialty,
};
```

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)
