
# ClinicRequest


## Properties

Name | Type
------------ | -------------
`name` | string
`description` | string
`phone` | string
`address` | [AddressResponse](AddressResponse.md)
`email` | string
`taxCode` | string
`website` | string
`accountNumber` | string
`bankName` | string

## Example

```typescript
import type { ClinicRequest } from ''

// TODO: Update the object below with actual values
const example = {
  "name": null,
  "description": null,
  "phone": null,
  "address": null,
  "email": null,
  "taxCode": null,
  "website": null,
  "accountNumber": null,
  "bankName": null,
} satisfies ClinicRequest

console.log(example)

// Convert the instance to a JSON string
const exampleJSON: string = JSON.stringify(example)
console.log(exampleJSON)

// Parse the JSON string back to an object
const exampleParsed = JSON.parse(exampleJSON) as ClinicRequest
console.log(exampleParsed)
```

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


