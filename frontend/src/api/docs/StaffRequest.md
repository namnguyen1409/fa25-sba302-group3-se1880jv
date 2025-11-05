
# StaffRequest


## Properties

Name | Type
------------ | -------------
`phone` | string
`fullName` | string
`departmentId` | string
`specialtyId` | string
`staffType` | string
`positionId` | string
`licenseNumber` | string
`experienceYears` | number
`education` | string
`bio` | string
`joinedDate` | Date
`email` | string

## Example

```typescript
import type { StaffRequest } from ''

// TODO: Update the object below with actual values
const example = {
  "phone": null,
  "fullName": null,
  "departmentId": null,
  "specialtyId": null,
  "staffType": null,
  "positionId": null,
  "licenseNumber": null,
  "experienceYears": null,
  "education": null,
  "bio": null,
  "joinedDate": null,
  "email": null,
} satisfies StaffRequest

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as StaffRequest
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


