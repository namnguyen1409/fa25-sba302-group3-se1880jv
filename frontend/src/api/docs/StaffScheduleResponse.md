# StaffScheduleResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **string** |  | [optional] [default to undefined]
**dayOfWeek** | **string** |  | [optional] [default to undefined]
**startTime** | [**LocalTime**](LocalTime.md) |  | [optional] [default to undefined]
**endTime** | [**LocalTime**](LocalTime.md) |  | [optional] [default to undefined]
**available** | **boolean** |  | [optional] [default to undefined]
**staff** | [**StaffResponse**](StaffResponse.md) |  | [optional] [default to undefined]
**room** | [**RoomResponse**](RoomResponse.md) |  | [optional] [default to undefined]
**date** | **string** |  | [optional] [default to undefined]
**status** | **string** |  | [optional] [default to undefined]
**note** | **string** |  | [optional] [default to undefined]

## Example

```typescript
import { StaffScheduleResponse } from './api';

const instance: StaffScheduleResponse = {
    id,
    dayOfWeek,
    startTime,
    endTime,
    available,
    staff,
    room,
    date,
    status,
    note,
};
```

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)
