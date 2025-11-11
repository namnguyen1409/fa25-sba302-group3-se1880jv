# StaffScheduleTemplateRequest


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**staffId** | **string** |  | [optional] [default to undefined]
**staffStaffType** | **string** |  | [optional] [default to undefined]
**staffLicenseNumber** | **string** |  | [optional] [default to undefined]
**staffExperienceYears** | **number** |  | [optional] [default to undefined]
**staffEducation** | **string** |  | [optional] [default to undefined]
**staffBio** | **string** |  | [optional] [default to undefined]
**staffJoinedDate** | **string** |  | [optional] [default to undefined]
**dayOfWeek** | **string** |  | [optional] [default to undefined]
**startTime** | [**LocalTime**](LocalTime.md) |  | [optional] [default to undefined]
**endTime** | [**LocalTime**](LocalTime.md) |  | [optional] [default to undefined]
**active** | **boolean** |  | [optional] [default to undefined]
**roomId** | **string** |  | [optional] [default to undefined]

## Example

```typescript
import { StaffScheduleTemplateRequest } from './api';

const instance: StaffScheduleTemplateRequest = {
    staffId,
    staffStaffType,
    staffLicenseNumber,
    staffExperienceYears,
    staffEducation,
    staffBio,
    staffJoinedDate,
    dayOfWeek,
    startTime,
    endTime,
    active,
    roomId,
};
```

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)
