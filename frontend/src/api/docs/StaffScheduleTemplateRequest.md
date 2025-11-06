
# StaffScheduleTemplateRequest


## Properties

Name | Type
------------ | -------------
`staffId` | string
`staffStaffType` | string
`staffLicenseNumber` | string
`staffExperienceYears` | number
`staffEducation` | string
`staffBio` | string
`staffJoinedDate` | Date
`dayOfWeek` | string
`startTime` | [LocalTime](LocalTime.md)
`endTime` | [LocalTime](LocalTime.md)
`active` | boolean
`roomId` | string

## Example

```typescript
import type { StaffScheduleTemplateRequest } from ''

// TODO: Update the object below with actual values
const example = {
  "staffId": null,
  "staffStaffType": null,
  "staffLicenseNumber": null,
  "staffExperienceYears": null,
  "staffEducation": null,
  "staffBio": null,
  "staffJoinedDate": null,
  "dayOfWeek": null,
  "startTime": null,
  "endTime": null,
  "active": null,
  "roomId": null,
} satisfies StaffScheduleTemplateRequest

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as StaffScheduleTemplateRequest
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


