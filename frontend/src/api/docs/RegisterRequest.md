
# RegisterRequest


## Properties

Name | Type
------------ | -------------
`username` | string
`fullName` | string
`email` | string
`password` | string
`phone` | string
`dob` | Date
`gender` | string
`address` | [AddressResponse](AddressResponse.md)

## Example

```typescript
import type { RegisterRequest } from ''

// TODO: Update the object below with actual values
const example = {
  "username": null,
  "fullName": null,
  "email": null,
  "password": null,
  "phone": null,
  "dob": null,
  "gender": null,
  "address": null,
} satisfies RegisterRequest

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as RegisterRequest
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


