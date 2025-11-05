
# StaffResponse


## Properties

Name | Type
------------ | -------------
`specialty` | [SpecialtyResponse](SpecialtyResponse.md)
`staffType` | string
`position` | [PositionResponse](PositionResponse.md)
`licenseNumber` | string
`experienceYears` | number
`education` | string
`bio` | string
`joinedDate` | Date
`email` | string
`department` | [DepartmentResponse](DepartmentResponse.md)
`id` | string

## Example

```typescript
import type { StaffResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "specialty": null,
  "staffType": null,
  "position": null,
  "licenseNumber": null,
  "experienceYears": null,
  "education": null,
  "bio": null,
  "joinedDate": null,
  "email": null,
  "department": null,
  "id": null,
} satisfies StaffResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as StaffResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


