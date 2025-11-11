# StaffScheduleTemplateResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **string** |  | [optional] [default to undefined]
**dayOfWeek** | **string** |  | [optional] [default to undefined]
**startTime** | [**LocalTime**](LocalTime.md) |  | [optional] [default to undefined]
**endTime** | [**LocalTime**](LocalTime.md) |  | [optional] [default to undefined]
**active** | **boolean** |  | [optional] [default to undefined]
**staff** | [**StaffResponse**](StaffResponse.md) |  | [optional] [default to undefined]
**room** | [**RoomResponse**](RoomResponse.md) |  | [optional] [default to undefined]

## Example

```typescript
import { StaffScheduleTemplateResponse } from './api';

const instance: StaffScheduleTemplateResponse = {
    id,
    dayOfWeek,
    startTime,
    endTime,
    active,
    staff,
    room,
};
```

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)
