# AppointmentResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **string** |  | [optional] [default to undefined]
**patient** | [**PatientResponse**](PatientResponse.md) |  | [optional] [default to undefined]
**specialty** | [**SpecialtyResponse**](SpecialtyResponse.md) |  | [optional] [default to undefined]
**type** | **string** |  | [optional] [default to undefined]
**status** | **string** |  | [optional] [default to undefined]
**source** | **string** |  | [optional] [default to undefined]
**note** | **string** |  | [optional] [default to undefined]
**queueTicket** | [**QueueTicketResponse**](QueueTicketResponse.md) |  | [optional] [default to undefined]
**createdDate** | **string** |  | [optional] [default to undefined]

## Example

```typescript
import { AppointmentResponse } from './api';

const instance: AppointmentResponse = {
    id,
    patient,
    specialty,
    type,
    status,
    source,
    note,
    queueTicket,
    createdDate,
};
```

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)
