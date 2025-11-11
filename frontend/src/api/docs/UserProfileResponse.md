
# UserProfileResponse


## Properties

Name | Type
------------ | -------------
`id` | string
`createdDate` | Date
`lastModifiedDate` | Date
`userUsername` | string
`userEmail` | string
`phone` | string
`fullName` | string
`dateOfBirth` | Date
`address` | [AddressResponse](AddressResponse.md)
`avatarUrl` | string

## Example

```typescript
import type { UserProfileResponse } from ''

// TODO: Update the object below with actual values
const example = {
  "id": null,
  "createdDate": null,
  "lastModifiedDate": null,
  "userUsername": null,
  "userEmail": null,
  "phone": null,
  "fullName": null,
  "dateOfBirth": null,
  "address": null,
  "avatarUrl": null,
} satisfies UserProfileResponse

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as UserProfileResponse
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


