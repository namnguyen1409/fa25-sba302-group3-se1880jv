# LabOrderResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **string** |  | [optional] [default to undefined]
**patient** | [**PatientResponse**](PatientResponse.md) |  | [optional] [default to undefined]
**requestedBy** | [**StaffResponse**](StaffResponse.md) |  | [optional] [default to undefined]
**examinationId** | **string** |  | [optional] [default to undefined]
**status** | **string** |  | [optional] [default to undefined]
**orderCode** | **string** |  | [optional] [default to undefined]
**results** | [**Set&lt;LabTestResultResponse&gt;**](LabTestResultResponse.md) |  | [optional] [default to undefined]
**room** | [**RoomResponse**](RoomResponse.md) |  | [optional] [default to undefined]
**assignedStaff** | [**StaffResponse**](StaffResponse.md) |  | [optional] [default to undefined]

## Example

```typescript
import { LabOrderResponse } from './api';

const instance: LabOrderResponse = {
    id,
    patient,
    requestedBy,
    examinationId,
    status,
    orderCode,
    results,
    room,
    assignedStaff,
};
```

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)
