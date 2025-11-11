# DispenseRecordResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **string** |  | [optional] [default to undefined]
**prescription** | [**PrescriptionResponse**](PrescriptionResponse.md) |  | [optional] [default to undefined]
**dispensedBy** | [**StaffResponse**](StaffResponse.md) |  | [optional] [default to undefined]
**status** | **string** |  | [optional] [default to undefined]
**dispensedAt** | **string** |  | [optional] [default to undefined]
**totalCost** | **number** |  | [optional] [default to undefined]
**note** | **string** |  | [optional] [default to undefined]
**patient** | [**PatientResponse**](PatientResponse.md) |  | [optional] [default to undefined]
**room** | [**RoomResponse**](RoomResponse.md) |  | [optional] [default to undefined]

## Example

```typescript
import { DispenseRecordResponse } from './api';

const instance: DispenseRecordResponse = {
    id,
    prescription,
    dispensedBy,
    status,
    dispensedAt,
    totalCost,
    note,
    patient,
    room,
};
```

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)
